import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { PropiedadesComponent } from './components/propiedades/propiedades.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'api/propiedades', component: PropiedadesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
