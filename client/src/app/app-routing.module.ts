import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {PlayerComponent} from "./components/player/player.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {SearchComponent} from "./components/search/search.component";
import {UploadComponent} from "./upload/upload.component";

const routes: Routes = [{
  path: '',
  pathMatch: 'full',
  redirectTo: 'home'
}, {
  path: 'home',
  component: HomeComponent
},
  {
    path: 'player',
    component: PlayerComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'upload',
    component: UploadComponent
  },
  {
    path: 'search',
    component: SearchComponent
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
