import {Component, OnInit} from '@angular/core';
import {ChatListComponent} from "../../components/chat-list/chat-list.component";
import {ChatResponse} from "../../services/models/chat-response";
import {ChatService} from "../../services/services/chat.service";
import {KeycloakService} from "../../utils/keycloak/keycloak.service";
import {MessageService} from "../../services/services/message.service";
import { MessageResponse } from 'src/app/services/models/message-response';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-main',
  imports: [
    ChatListComponent,
      CommonModule
  ],
  templateUrl: './main.component.html',
  standalone: true,
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  chats: Array<ChatResponse> = [];
  selectedChat: ChatResponse = {};
  chatMessages: MessageResponse[] = [];

  constructor(
    private chatService: ChatService,
    private keycloakService: KeycloakService,
    private messsageService: MessageService
  ) {
  }


  ngOnInit() {
    this.getAllChats();
  }

  private getAllChats() {
    this.chatService.getChatByReceiver().subscribe({next: (res)=>{this.chats = res;}})
  }

  logout() {
  this.keycloakService.logout();
  }

  userProfile() {
  this.keycloakService.accountManagement();
  }

  chatSelected(chatResponse: ChatResponse) {
this.selectedChat = chatResponse;
this.getAllChatMessages(chatResponse.id as string);
this.setMessagesToSeen();
//this.selectedChat.unreadCount = 0;
  }

  private getAllChatMessages(chatId: string) {
this.messsageService.getMessage({
  'chat-id' : chatId,
}).subscribe({next: (messages) => {
  this.chatMessages = messages;
  }})
  }

  private setMessagesToSeen() {

  }

  isSelfMessage(message: MessageResponse) {
      return message.senderId === this.keycloakService.userId;
  }

}
