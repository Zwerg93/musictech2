import { Component, OnInit } from '@angular/core';
import {AudioService} from "../../services/audio.service";
import {CloudService} from "../../services/cloud.service";
import {PlayerService} from "../../services/player.service";

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.scss']
})
export class PlayerComponent implements OnInit {

  constructor(public audioService: AudioService, public cloudService: CloudService, public playerService: PlayerService  ) {
  }

  ngOnInit(): void {
  }

  play() {
    console.log(this.cloudService.songlist)
    this.playerService.play();
  }
  pause(){
    this.playerService.pause();
  }

  currentSongClickedon(i) {
    this.playerService.currentSongName = this.cloudService.songlist[i].name;
    this.playerService.currentArtist = this.cloudService.songlist[i].name;
  }
}
