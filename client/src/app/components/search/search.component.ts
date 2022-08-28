import {Component, OnInit} from '@angular/core';
import {debounceTime, delay, distinctUntilChanged, map, mergeMap, of, Subject, Subscription} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  searchstring: String;
  itemList: any;
  private errors: any;
  keyUp$;


  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {

  }


  cancelSearch() {

    (<HTMLInputElement>document.getElementById("searchinput")).value = '';

  }

  myMethod() {
    //console.log(this.searchstring)
    if (this.searchstring != "") {
      ///api/youtube/download/
      //http://localhost:8080/youtube/download/
      this.http.get('http://localhost:8080/youtube/download/' + this.searchstring).toPromise().then((response: any) => {
        this.itemList = response[0];
       // console.log(this.searchstring)
        console.table(response);

        //for (const i in response[0].items) {
        //  console.log(response[0].items[i].id.videoId);

        //  console.log(response[0].items[i].snippet.title);

        //}

      });
    }


  }

  test() {
    this.searchstring = (<HTMLInputElement>document.getElementById("input")).value;
    this.myMethod()
  }

  download(number: any, title: String) {
//http://localhost:8080/youtube/download/mp3/
    this.http.get('http://localhost:8080/youtube/download/mp3/' + number + '/' + title + '').subscribe(
      result => {
      },
      error => {
        this.errors = error;
        document.getElementById("showresult")!.innerHTML = "<fieldset>\n" +
          "    <div >\n" +
          "<p>Error. Pls try again!</p>" +
          "    </div>\n" +
          "  </fieldset>"
      },
      () => {
        document.getElementById("showresult")!.innerHTML = "<fieldset>\n" +
          "    <div >\n" +
          "<p>Succes!</p>" +
          "    </div>\n" +
          "  </fieldset>"

      });
    console.log(number);
  }
}


/*
  document.getElementById("output")!.innerHTML += '<div class=" d-flex justify-content-center">\n' +
            '
            '</div>'

            <div class="ui search">
  <div class="ui icon input">
    <input type="text" class="prompt"
           (keyup)="keyUp$.next($any($event).target.value)">
    <i class="search icon"></i>
  </div>
</div>
 */
