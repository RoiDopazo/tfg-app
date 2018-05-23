import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExploreTabPage } from './exploreTab';

@NgModule({
  declarations: [
    ExploreTabPage,
  ],
  imports: [
    IonicPageModule.forChild(ExploreTabPage),
  ],
})
export class ExploreTabPageModule {}
