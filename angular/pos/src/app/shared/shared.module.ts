import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorMessageComponent } from './error-message/error-message.component';
import {MatFormFieldModule} from '@angular/material/form-field';
<<<<<<< HEAD
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule} from '@angular/material/button';
import { SearchComponent } from './search/search.component';
import { ReactiveFormsModule } from "@angular/forms";
import { NgSelectModule } from '@ng-select/ng-select';


=======
import { SearchComponent } from './search/search.component';
import { ReactiveFormsModule } from "@angular/forms";
import { NgSelectModule } from '@ng-select/ng-select';
>>>>>>> 85845f76450982ca50f37d0ced4ac7be71947481
@NgModule({
  declarations: [ErrorMessageComponent, SearchComponent],
  imports: [
    CommonModule,
    MatFormFieldModule,
<<<<<<< HEAD
    MatDialogModule,
    MatButtonModule
=======
>>>>>>> 85845f76450982ca50f37d0ced4ac7be71947481
    ReactiveFormsModule,
    NgSelectModule
  ],
  exports: [ErrorMessageComponent, SearchComponent]
})
export class SharedModule { }
