import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent{
  perfil: boolean = sessionStorage.getItem('perfil') === 'true';

  constructor(public router: Router) {
    if(!this.router.url.includes('/perfil'))
      this.perfil = false;
    // router.events.subscribe((event) => {
    //   if (event instanceof NavigationEnd && !this.router.url.includes('/perfil')) {
    //     this.perfil = false; // Fechar o accordion se a rota não for a página de perfil
    //   }
    // });
  }

  togglePerfilAccordion() {
    this.perfil = !this.perfil;
    sessionStorage.setItem('perfil', this.perfil.toString());
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
    this.perfil = false;
    sessionStorage.setItem('perfil', 'false');
  }

  // perfil: boolean = false;

  // constructor(public router: Router, private elementRef: ElementRef) {
  //   router.events.subscribe((event) => {
  //     if (event instanceof NavigationEnd && !this.router.url.includes('/perfil')) {
  //       this.perfil = false; // Fechar o accordion se a rota não for a página de perfil
  //     }
  //   });
  // }

  // togglePerfilAccordion() {
  //   this.perfil = !this.perfil;
  // }

  // navigateTo(route: string) {
  //   this.router.navigate([route]);
  //   this.perfil = false; // Fechar o accordion ao navegar para outra página
  // }

  // @HostListener('document:click', ['$event'])
  // onClick(event: MouseEvent) {
  //   if (!this.elementRef.nativeElement.contains(event.target)) {
  //     // Verifica se o clique ocorreu fora do accordion e fecha o accordion
  //     this.perfil = false;
  //   }
  // }
}