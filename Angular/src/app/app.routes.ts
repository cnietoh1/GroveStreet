import { Routes } from '@angular/router';
import { PropiedadesComponent } from './components/propiedades/propiedades.component';
import { LoginComponent } from './components/login/login.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { AuthGuard } from './services/auth.guard';
import { RegistroComponent } from './components/registro/registro.component';
import { PropiedadNuevaComponent } from './components/propiedad-nueva/propiedad-nueva.component';
import { PropiedadesListComponent } from './components/propiedades-list/propiedades-list.component';

export const APP_ROUTES: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegistroComponent },
  { path: 'propiedades', component: PropiedadesComponent, canActivate: [AuthGuard] },
  { path: 'propiedades/new', component: PropiedadNuevaComponent, canActivate: [AuthGuard] },
  { path: 'propiedades/edit/:id', component: PropiedadNuevaComponent, canActivate: [AuthGuard] },
  { path: 'propiedades/list', component: PropiedadesListComponent, canActivate: [AuthGuard] },
];
