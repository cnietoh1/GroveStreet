import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LandingPageService } from '../../services/landing-page.service';
import {NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [FormsModule, NgOptimizedImage],
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
})
export class LandingPageComponent {
  ciudad: string = '';
  provincia: string = '';

  constructor(
    private router: Router,
    private landingPageService: LandingPageService
  ) {}

  buscar(): void {
    this.router.navigate(['/api/propiedades'], {
      queryParams: { ciudad: this.ciudad, provincia: this.provincia },
    });
  }
}
