import { Component, Input , Output, EventEmitter  } from '@angular/core';
import { ChatResponse } from "../../services/models/chat-response";
import { CommonModule, DatePipe } from '@angular/common';  // Correct import here
import {UserResponse} from "../../services/models/user-response";
import {ChatService} from "../../services/services/chat.service";
import {UserService} from "../../services/services/user.service";

@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css'],
  standalone: true,
  imports: [CommonModule, DatePipe]  // Ensure DatePipe is imported here as well
})
export class ChatListComponent {
  @Input() chats: ChatResponse[] = [];
  searchNewContact=false;
  contacts: Array<UserResponse> = [];
  @Output() chatSelected = new EventEmitter<ChatResponse>();  // Correct output declaration

  constructor(
    private chatService: ChatService,

    private userService: UserService) { }
  searchContact(){
    this.userService.getAllUsers().subscribe({
      next: (users) => {
        this.contacts = users;
        this.searchNewContact=true;
      }
    })
  }

  chatClicked(chat: any) {
this.chatSelected.emit(chat);
  }
  trackChat(index: number, chat: ChatResponse): string {
    return chat.id ?? index.toString();
  }
  trackContact(index: number, contact: any): string {
    return contact.id;
  }
  selectContact(contact: any) {
    console.log("Selected contact:", contact);
  }

  wrapMessage(lastMessage: string | undefined):string {
    if (lastMessage && lastMessage.length <= 20) {
      return lastMessage;
    }
    return lastMessage?.substring(0, 17) + '...';
  }


}
