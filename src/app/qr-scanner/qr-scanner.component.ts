import { Component } from '@angular/core';

@Component({
  selector: 'app-qr-scanner',
  templateUrl: './qr-scanner.component.html',
  styleUrls: ['./qr-scanner.component.css']
})
export class QrScannerComponent {
  qrResult: string | null = null;

  // La méthode peut accepter un événement de type `any`, selon ce qui est renvoyé par le scanner
  onQrCodeScanned(event: any): void {
    // Si l'événement est un objet, tu peux vérifier la structure et extraire la chaîne de caractères
    if (event && event.text) {
      this.qrResult = event.text;  // Stocke le texte du QR Code scanné
    } else {
      this.qrResult = event;  // Si c'est déjà une chaîne de caractères
    }
  }
}
