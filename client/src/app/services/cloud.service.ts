import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class CloudService {
  constructor(private http: HttpClient) {
  }

  public songlist: { url: string, name: string, artist: string }[] = [];
  public songlist_TMP: { url: string, name: string, artist: string }[] = [];
  public playlists: any;

  onload() {
    ///api/song/all
    //http://localhost:8080/song/all
    this.http.get('http://83.215.72.88:8080/song/all').toPromise().then((response: any) => {
      this.songlist = response;
      this.songlist_TMP = response;
      console.table(this.songlist);
    });
    sessionStorage.setItem('username', 'marcel');

    //if (sessionStorage.getItem('username') != null) {
      // /api/user/getPlalist/
      //http://localhost:8080/
      this.http.get('http://83.215.72.88:8080/user/getPlalist/' + sessionStorage.getItem('username')).toPromise().then((response: any) => {

        this.playlists = response;
      //  console.table(this.playlists[0].songList[0].url + " Playlists");

        //  this.files = this.itemList[0].songList;
      })
    //}
  }

  public getFiles() {
    console.log(this.songlist)
    return of(this.songlist);
  }


}


