import { Component, OnInit, Input, Output } from '@angular/core';
import { FormControl, FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchForm: FormGroup;
  results: any;
  @Output() changeResult: EventEmitter<any> = new EventEmitter<any>();
  @Input() url: string;
  @Input() fields: string[];

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.searchForm = this.fb.group({
      queryField: new FormControl('', [])
    });
  }

  onSearch(event: any) {
    if (event.keyCode === 13) {
      const params = {
        search: this.searchForm.get('queryField').value,
        fields: this.fields
      };
      this.http.get(this.url, { params }).subscribe(result => {
        if (result !== undefined) {
          this.results = result;
          this.changeResult.emit(this.results);
        }
      });
    }
  }
}
