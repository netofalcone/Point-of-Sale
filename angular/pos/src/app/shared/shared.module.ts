import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorMessageComponent } from './error-message/error-message.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule} from '@angular/material/button';
import { SearchComponent } from './search/search.component';
import { ReactiveFormsModule } from "@angular/forms";
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  declarations: [ErrorMessageComponent, SearchComponent],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatDialogModule,
    MatButtonModule
    ReactiveFormsModule,
    NgSelectModule
  ],
  exports: [ErrorMessageComponent, SearchComponent]
})
export class SharedModule { }
