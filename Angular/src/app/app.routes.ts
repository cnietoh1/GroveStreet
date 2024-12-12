import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { PropiedadesComponent } from './components/propiedades/propiedades.component';

export const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent,
  },
  {
    path: 'api/propiedades',
    component: PropiedadesComponent,
  },
];
