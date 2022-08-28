import { Component } from '@angular/core';
import {CloudService} from "./services/cloud.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client';


  constructor(cloudService: CloudService) {
    cloudService.onload()
  }
}
