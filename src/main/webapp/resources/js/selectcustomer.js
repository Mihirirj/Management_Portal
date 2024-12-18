define(["dojo/request/xhr","dojo/dom","dojo/on","dojo/domReady!"],
       function(xhr,dom,on){
             
              function updateCustomer(){
                     dom.byId("custForm").submit();
              }
             
              function callAjaxListing(){
                     alert("Will send ajax call");
                     xhr("ajax/list",{method:"post",data:{'custId':'1'},preventCache:true}).then(
                           function(data){
                                  dom.byId("searchResults").innerHTML=data;
                                  on(dom.byId("custEditBtn"),"click",updateCustomer);
                           },
                           function(err){
                                  alert("error"+err);
                           },
                           function(evt){
                           }
                     );
              }
              on(dom.byId("custSearchBtn"),"click",callAjaxListing);
       }
);