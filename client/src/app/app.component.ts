import { Component } from '@angular/core';
import {CloudService} from "./services/cloud.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client';


  constructor(public cloudService: CloudService) {
    cloudService.onload()
  }

  resetSonglist() {
    this.cloudService.songlist = this.cloudService.songlist_TMP;
  }
}
