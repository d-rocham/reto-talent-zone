import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './views/storeView/components/header/header.component';
import { CartComponent } from './views/storeView/components/cart/cart.component';
import { ProductTableComponent } from './views/storeView/components/product-table/product-table.component';
import { ProductRowComponent } from './views/storeView/components/product-row/product-row.component';
import { PurchasedProductComponent } from './views/storeView/components/purchased-product/purchased-product.component';
import { StoreViewComponent } from './views/storeView/store-view/store-view.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CartComponent,
    ProductTableComponent,
    ProductRowComponent,
    PurchasedProductComponent,
    StoreViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
