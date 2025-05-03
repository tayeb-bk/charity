# enhanced_poster_generator.py
from flask import Flask, request, jsonify
import requests
from reportlab.lib.pagesizes import A4, letter
from reportlab.lib import colors
from reportlab.pdfgen import canvas
from reportlab.lib.units import inch
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.platypus import Paragraph, Frame
from reportlab.lib.enums import TA_CENTER, TA_LEFT
import os
from datetime import datetime
import random
import io
from PIL import Image

GEMINI_API_KEY = "AIzaSyARZpRViXd-KEPO2mJrw4Yzqm2b0Ujgkks"
GEMINI_URL = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-pro:generateContent?key={GEMINI_API_KEY}"

OUTPUT_DIR = r"C:\Users\pc\Desktop\charity back and front\charity\event"
os.makedirs(OUTPUT_DIR, exist_ok=True)

app = Flask(__name__)

# Define a set of color schemes for different event types
COLOR_SCHEMES = {
    "conference": {
        "primary": colors.Color(0.0, 0.2, 0.4),  # Dark blue
        "secondary": colors.Color(0.8, 0.3, 0.0),  # Orange
        "accent": colors.Color(0.0, 0.6, 0.8),  # Light blue
        "text": colors.black,
        "background": colors.white,
    },
    "concert": {
        "primary": colors.Color(0.5, 0.0, 0.5),  # Purple
        "secondary": colors.Color(0.8, 0.7, 0.0),  # Gold
        "accent": colors.Color(0.9, 0.1, 0.2),  # Crimson
        "text": colors.black,
        "background": colors.white,
    },
    "workshop": {
        "primary": colors.Color(0.2, 0.5, 0.3),  # Green
        "secondary": colors.Color(0.7, 0.9, 0.7),  # Light green
        "accent": colors.Color(0.1, 0.3, 0.6),  # Blue
        "text": colors.black,
        "background": colors.white,
    },
    "charity": {
        "primary": colors.Color(0.1, 0.5, 0.7),  # Blue
        "secondary": colors.Color(0.9, 0.7, 0.2),  # Gold
        "accent": colors.Color(0.0, 0.6, 0.4),  # Teal
        "text": colors.black,
        "background": colors.white,
    },
    "festival": {
        "primary": colors.Color(0.9, 0.2, 0.3),  # Red
        "secondary": colors.Color(1.0, 0.8, 0.0),  # Yellow
        "accent": colors.Color(0.2, 0.8, 0.4),  # Green
        "text": colors.black,
        "background": colors.white,
    },
    "default": {
        "primary": colors.Color(0.2, 0.2, 0.6),  # Navy blue
        "secondary": colors.Color(0.8, 0.4, 0.0),  # Orange
        "accent": colors.Color(0.0, 0.5, 0.5),  # Teal
        "text": colors.black,
        "background": colors.white,
    }
}

def call_gemini(prompt):
    """Call the Gemini API with the given prompt"""
    payload = {
        "contents": [{
            "parts": [{"text": prompt}]
        }],
        "generationConfig": {
            "temperature": 0.7,
            "topP": 0.8,
            "maxOutputTokens": 2048
        }
    }
    headers = {"Content-Type": "application/json"}
    
    try:
        response = requests.post(GEMINI_URL, json=payload, headers=headers)
        response.raise_for_status()
        text = response.json()["candidates"][0]["content"]["parts"][0]["text"]
        return text.strip()
    except Exception as e:
        print(f"Error calling Gemini API: {e}")
        # Return a fallback template if API call fails
        return """
# EVENT SPOTLIGHT

**TAGLINE: Experience the extraordinary!**

## ABOUT
Join us for this spectacular event featuring amazing activities and unforgettable moments. Connect with others who share your interests and make lasting memories.

## FEATURING
- Exclusive networking opportunities
- Expert-led sessions and demonstrations
- Special surprises and giveaways

## SCHEDULE
- Opening ceremony and welcome reception
- Main event activities and presentations
- Closing remarks and future announcements

## SPONSORS
Local Business Association, Community Partners, Industry Leaders

**DON'T MISS OUT – SECURE YOUR SPOT TODAY!**
        """

def parse_gemini_response(response_text):
    """Parse the response from Gemini into structured sections"""
    sections = {
        "tagline": "",
        "about": "",
        "featuring": [],
        "schedule": [],
        "sponsors": [],
        "call_to_action": ""
    }
    
    current_section = None
    
    for line in response_text.split('\n'):
        line = line.strip()
        if not line:
            continue
            
        lower_line = line.lower()
        
        if "tagline" in lower_line:
            current_section = "tagline"
            sections["tagline"] = line.split(":", 1)[1].strip() if ":" in line else line
        elif "about" in lower_line and ("##" in line or line.isupper()):
            current_section = "about"
        elif "featuring" in lower_line and ("##" in line or line.isupper()):
            current_section = "featuring"
        elif "schedule" in lower_line and ("##" in line or line.isupper()):
            current_section = "schedule"
        elif "sponsor" in lower_line and ("##" in line or line.isupper()):
            current_section = "sponsors"
        elif any(call_word in lower_line for call_word in ["don't miss", "register now", "join us", "book now", "secure your"]):
            if "call_to_action" not in lower_line:  # Avoid parsing the section header
                sections["call_to_action"] = line
        elif current_section == "about" and not any(header in lower_line for header in ["##", "tagline", "featuring", "schedule", "sponsor"]):
            if sections["about"]:
                sections["about"] += " " + line
            else:
                sections["about"] = line
        elif current_section == "featuring" and line.startswith("-"):
            sections["featuring"].append(line[1:].strip())
        elif current_section == "schedule" and line.startswith("-"):
            sections["schedule"].append(line[1:].strip())
        elif current_section == "sponsors" and line.startswith("-"):
            sections["sponsors"].append(line[1:].strip())
        elif current_section == "sponsors" and not any(header in lower_line for header in ["##", "call to action"]):
            # Split by commas for sponsors that might be in a comma-separated list
            if ',' in line:
                sponsors = [s.strip() for s in line.split(',')]
                sections["sponsors"].extend(sponsors)
            else:
                sections["sponsors"].append(line)
    
    # If we didn't find explicit sponsors but have text in that section
    if not sections["sponsors"] and current_section == "sponsors":
        sections["sponsors"] = ["Community Partners", "Local Businesses", "Industry Leaders"]
    
    # Ensure we have at least some items in each list
    if not sections["featuring"]:
        sections["featuring"] = ["Exclusive experiences", "Networking opportunities", "Special activities"]
    if not sections["schedule"]:
        sections["schedule"] = ["Welcome and introduction", "Main event activities", "Closing ceremony"]
    if not sections["sponsors"]:
        sections["sponsors"] = ["Community Partners", "Local Businesses", "Industry Leaders"]
    if not sections["call_to_action"]:
        sections["call_to_action"] = "DON'T MISS OUT – REGISTER TODAY!"
        
    return sections

def draw_background_design(c, width, height, color_scheme):
    """Draw a decorative background for the poster"""
    # Draw a header bar
    c.setFillColor(color_scheme["primary"])
    c.rect(0, height-2*inch, width, 2*inch, fill=1, stroke=0)
    
    # Draw footer bar
    c.setFillColor(color_scheme["primary"])
    c.rect(0, 0, width, 1.5*inch, fill=1, stroke=0)
    
    # Draw decorative elements
    c.setFillColor(color_scheme["secondary"])
    
    # Side accent
    c.rect(0, 1.5*inch, 0.5*inch, height-3.5*inch, fill=1, stroke=0)
    c.rect(width-0.5*inch, 1.5*inch, 0.5*inch, height-3.5*inch, fill=1, stroke=0)
    
    # Draw decorative shapes
    c.setFillColor(color_scheme["accent"])
    for _ in range(6):
        x = random.uniform(0.6*inch, width-0.6*inch)
        y = random.uniform(1.7*inch, height-2.2*inch)
        size = random.uniform(0.1*inch, 0.3*inch)
        shape_type = random.choice(["circle", "square", "triangle"])
        
        if shape_type == "circle":
            c.circle(x, y, size, fill=1, stroke=0)
        elif shape_type == "square":
            c.rect(x-size/2, y-size/2, size, size, fill=1, stroke=0)
        else:  # triangle
            c.setFillColor(color_scheme["accent"])
            p = c.beginPath()
            p.moveTo(x, y+size)
            p.lineTo(x-size, y-size)
            p.lineTo(x+size, y-size)
            p.close()
            c.drawPath(p, fill=1, stroke=0)

def generate_poster(event_data):
    """Generate a PDF poster for the event"""
    title = event_data.get("title", "Event Title")
    description = event_data.get("description", "Event Description")
    date_value = event_data.get("date", "")
    location = event_data.get("location", "Location")
    category = event_data.get("categories", "General")
    event_id = event_data.get("id", "unknown")
    price = event_data.get("price", "Free")
    organizer = event_data.get("organizer", "Event Organizers")
    contact = event_data.get("contact", "")
    website = event_data.get("website", "")
    
    # Additional event details if available
    capacity = event_data.get("capacity", "")
    age_restriction = event_data.get("age_restriction", "All ages welcome")
    dress_code = event_data.get("dress_code", "")
    speakers = event_data.get("speakers", [])
    activities = event_data.get("activities", [])
    
    # Parse date and time
    try:
        if isinstance(date_value, str) and date_value:
            if '.' in date_value:
                date_obj = datetime.strptime(date_value.split('.')[0], '%Y-%m-%d %H:%M:%S')
            else:
                # Try different formats
                try:
                    date_obj = datetime.strptime(date_value, '%Y-%m-%d %H:%M:%S')
                except ValueError:
                    try:
                        date_obj = datetime.strptime(date_value, '%Y-%m-%d')
                    except ValueError:
                        date_obj = datetime.now()  # Fallback
            
            event_date_str = date_obj.strftime('%A, %B %d, %Y')
            event_time = date_obj.strftime('%I:%M %p')  # 12-hour format with AM/PM
        else:
            event_date_str = str(date_value) if date_value else "Date TBA"
            event_time = "Time TBA"
    except Exception as e:
        print(f"Date parsing error: {e}")
        event_date_str = str(date_value) if date_value else "Date TBA"
        event_time = "Time TBA"
    
    # Determine the color scheme based on category
    if isinstance(category, list) and category:
        category_str = category[0].lower() if category[0] else "default"
    else:
        category_str = str(category).lower()
    
    color_scheme = next(
        (COLOR_SCHEMES[key] for key in COLOR_SCHEMES if key in category_str), 
        COLOR_SCHEMES["default"]
    )
    
    # Prepare speakers/performers info if available
    speakers_str = ""
    if speakers and isinstance(speakers, list):
        speakers_str = ", ".join(speakers[:3])  # Take up to 3 speakers
    elif speakers and isinstance(speakers, str):
        speakers_str = speakers
    
    # Prepare activities info if available
    activities_str = ""
    if activities and isinstance(activities, list):
        activities_str = ", ".join(activities[:3])  # Take up to 3 activities
    elif activities and isinstance(activities, str):
        activities_str = activities

    # Craft a detailed prompt for Gemini
    prompt = f"""
Create vibrant, compelling, and detailed content for an event poster with the following details:

EVENT: {title}

DESCRIPTION: {description}

DATE: {event_date_str}
TIME: {event_time}
LOCATION: {location}
CATEGORY: {category}
PRICE: {price}
ORGANIZER: {organizer}

{"FEATURING SPEAKERS: " + speakers_str if speakers_str else ""}
{"ACTIVITIES: " + activities_str if activities_str else ""}
{"CAPACITY: " + str(capacity) if capacity else ""}
{"AGE RESTRICTION: " + age_restriction if age_restriction else ""}
{"DRESS CODE: " + dress_code if dress_code else ""}

Structure the content as follows (be creative and compelling with each section):

1. TAGLINE: A short, catchy one-liner that captures the essence of the event
2. ABOUT: 2-3 sentences describing what makes this event special and why people should attend
3. FEATURING: 3-5 bullet points highlighting the most exciting elements of the event
4. SCHEDULE: 3-4 bullet points outlining key activities or times
5. SPONSORS: List 3-5 example sponsor organizations (create realistic fictional ones if needed)
6. CALL TO ACTION: A compelling final statement encouraging immediate registration

The tone should be:
- Exciting and energetic
- Professional but inviting
- Appropriate for the event category ({category})

Be specific, use vivid language, and create a sense of exclusivity and urgency.
"""

    # Call Gemini API to generate poster content
    generated_text = call_gemini(prompt)
    print(f"Generated text: {generated_text}")  # For debugging
    
    # Parse the generated content
    parsed_content = parse_gemini_response(generated_text)
    
    # Create PDF
    filename = f"event_poster_{event_id}.pdf"
    filepath = os.path.join(OUTPUT_DIR, filename)
    
    # Use A4 for a standard poster size
    pagesize = A4
    width, height = pagesize
    
    # Create PDF canvas
    c = canvas.Canvas(filepath, pagesize=pagesize)
    
    # Draw background design
    draw_background_design(c, width, height, color_scheme)
    
    # Define styles
    styles = getSampleStyleSheet()
    title_style = ParagraphStyle(
        'EventTitle',
        parent=styles['Heading1'],
        fontSize=24,
        alignment=TA_CENTER,
        textColor=colors.white,
        spaceAfter=12
    )
    
    subtitle_style = ParagraphStyle(
        'Subtitle',
        parent=styles['Heading2'],
        fontSize=16,
        alignment=TA_CENTER,
        textColor=colors.white
    )
    
    section_title_style = ParagraphStyle(
        'SectionTitle',
        parent=styles['Heading2'],
        fontSize=14,
        alignment=TA_LEFT,
        textColor=color_scheme['primary'],
        spaceAfter=6
    )
    
    body_style = ParagraphStyle(
        'Body',
        parent=styles['Normal'],
        fontSize=11,
        alignment=TA_LEFT,
        textColor=color_scheme['text'],
        spaceAfter=6
    )
    
    feature_style = ParagraphStyle(
        'Feature',
        parent=styles['Normal'],
        fontSize=11,
        alignment=TA_LEFT,
        textColor=color_scheme['text'],
        leftIndent=20,
        bulletIndent=10,
        spaceAfter=3
    )
    
    cta_style = ParagraphStyle(
        'CallToAction',
        parent=styles['Heading3'],
        fontSize=16,
        alignment=TA_CENTER,
        textColor=colors.white,
        spaceAfter=6
    )
    
    # Draw title in the header area
    title_frame = Frame(inch, height-2*inch, width-2*inch, 1.5*inch, showBoundary=0)
    title_frame.addFromList([Paragraph(title, title_style)], c)
    
    # Draw tagline
    if parsed_content["tagline"]:
        tagline_frame = Frame(inch, height-2.5*inch, width-2*inch, 0.5*inch, showBoundary=0)
        tagline_frame.addFromList([Paragraph(parsed_content["tagline"], subtitle_style)], c)
    
    # Draw event details
    c.setFillColor(color_scheme["text"])
    details_y = height - 3*inch
    details_text = f"<b>Date:</b> {event_date_str}<br/>" \
                   f"<b>Time:</b> {event_time}<br/>" \
                   f"<b>Location:</b> {location}<br/>" \
                   f"<b>Price:</b> {price}"
    
    details_frame = Frame(inch, details_y-1.2*inch, width-2*inch, 1.2*inch, showBoundary=0)
    details_frame.addFromList([Paragraph(details_text, body_style)], c)
    
    # Draw About section
    about_y = details_y - 1.5*inch
    about_frame = Frame(inch, about_y-1.2*inch, width-2*inch, 1.2*inch, showBoundary=0)
    about_frame.addFromList([
        Paragraph("ABOUT", section_title_style),
        Paragraph(parsed_content["about"], body_style)
    ], c)
    
    # Draw Featuring section
    featuring_y = about_y - 1.5*inch
    featuring_frame = Frame(inch, featuring_y-1.5*inch, width-2*inch, 1.5*inch, showBoundary=0)
    featuring_paragraphs = [Paragraph("FEATURING", section_title_style)]
    
    for item in parsed_content["featuring"][:5]:  # Limit to 5 items
        featuring_paragraphs.append(Paragraph(f"• {item}", feature_style))
    
    featuring_frame.addFromList(featuring_paragraphs, c)
    
    # Draw Schedule section
    schedule_y = featuring_y - 1.8*inch
    schedule_frame = Frame(inch, schedule_y-1.5*inch, width-2*inch, 1.5*inch, showBoundary=0)
    schedule_paragraphs = [Paragraph("SCHEDULE", section_title_style)]
    
    for item in parsed_content["schedule"][:4]:  # Limit to 4 items
        schedule_paragraphs.append(Paragraph(f"• {item}", feature_style))
    
    schedule_frame.addFromList(schedule_paragraphs, c)
    
    # Draw sponsors section
    sponsors_y = schedule_y - 1.5*inch
    if sponsors_y > 2*inch:  # Check if there's enough space
        sponsors_frame = Frame(inch, sponsors_y-1*inch, width-2*inch, 1*inch, showBoundary=0)
        sponsors_text = "SPONSORS: " + ", ".join(parsed_content["sponsors"][:5])  # Limit to 5 sponsors
        sponsors_frame.addFromList([Paragraph(sponsors_text, body_style)], c)
    
    # Draw call to action in the footer
    cta_frame = Frame(inch, 0.25*inch, width-2*inch, inch, showBoundary=0)
    cta_frame.addFromList([Paragraph(parsed_content["call_to_action"], cta_style)], c)
    
    # Add contact information if available
    if contact or website:
        contact_text = []
        if contact:
            contact_text.append(f"Contact: {contact}")
        if website:
            contact_text.append(f"Website: {website}")
        
        contact_paragraph = Paragraph(" | ".join(contact_text), ParagraphStyle(
            'Contact',
            parent=styles['Normal'],
            fontSize=9,
            alignment=TA_CENTER,
            textColor=colors.white
        ))
        
        contact_frame = Frame(inch, 0.1*inch, width-2*inch, 0.3*inch, showBoundary=0)
        contact_frame.addFromList([contact_paragraph], c)
    
    c.showPage()
    c.save()
    
    return filepath

@app.route('/generate_event_poster', methods=['POST'])
def generate_event_poster():
    """API endpoint to generate an event poster"""
    event_data = request.get_json()
    if not event_data:
        return jsonify({"error": "No event data provided"}), 400

    try:
        poster_path = generate_poster(event_data)
        return jsonify({
            "message": "Event poster PDF generated successfully", 
            "poster_path": poster_path,
            "status": "success"
        })
    except Exception as e:
        import traceback
        error_details = traceback.format_exc()
        return jsonify({
            "error": str(e),
            "details": error_details,
            "status": "error"
        }), 500

@app.route('/health', methods=['GET'])
def health_check():
    """Simple health check endpoint"""
    return jsonify({"status": "ok", "message": "Poster generator service is running"})

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5001)