import {Component, OnInit} from '@angular/core';
import {AudioService} from "../../services/audio.service";
import {PlayerService} from "../../services/player.service";
import {CloudService} from "../../services/cloud.service";
import {timer} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  playlist: any;
  state;
  toggle: any;


  constructor(public audioService: AudioService, public cloudService: CloudService, public playerService: PlayerService) {
    this.state = playerService.state;
  }

  ngOnInit(): void {

    timer(200).subscribe(x => {
      this.playlist = this.cloudService.playlists;
    })

  }

  play() {
    console.log(this.cloudService.songlist)
    this.playerService.play();
  }
  pause(){
    this.playerService.pause();
  }


  console(test: string) {
    console.log(test)
  }

  getPlaylist(index) {
    console.log(this.cloudService.playlists[index].songList);
    this.cloudService.songlist = this.cloudService.playlists[index].songList;

  }
}
