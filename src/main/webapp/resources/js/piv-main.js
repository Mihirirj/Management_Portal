function numericValidate(obj,event)
{
	var key = event.keyCode;
	
	if((key>=48 && key<=57)|| key==190)	{}
	else
	{
		var val = obj.value.toUpperCase();
		var keyVal = String.fromCharCode ( event.keyCode )
		var index = val.indexOf(keyVal);
		if(index!=-1)
		{
			obj.value = val.substring(0,index)+val.substring(index+1);
		}
	}

}

//Allow only Numbers (0-9) and retrict to two decimal position
function restrictToTwoDecimalPossition(obj,event){
    event = event || window.event;
    if (event.which!="undefined"){
        if (event.which!=0){
            var key = event.keyCode || event.which;
            if( (key != 46) && (key < 48 || key > 57)){
                return false;
            }
            if(obj.value.indexOf('.')!=(-1) && key==46){
                return false;
            }
            if(key!=46){
                if(obj.value.indexOf('.')!=(-1)){
                    if(obj.value.substr(obj.value.indexOf('.')).length>2)// number
																			// of
																			// decimal
																			// places
                    {
                        return false;
                    }
                }else {
                    if(obj.value.length>10){
                        return false;
                    }
                }
            }
            if(obj.value.indexOf('.')!=(-1)){
                if(obj.value.substr(obj.value.indexOf('.')).length>2){
                    return false;
                }
            }
            return true;
        }// equal to zero
        return true;
    }// equal to undefind
    return true;
}

function formatCurrency(num) {
	//alert('format'+num)
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}

function formatValue(obj)
{
	//alert('formatValue'+obj);
	var objVal = obj.value;
	obj.value = formatCurrency(objVal);
}

function pivFormValidate()
{
	//alert('pivFormValidate'+document.getElementById("cmbPivType").value);
	if(document.getElementById("cmbPivType").value=="-1")
	{
		document.getElementById("cmbPivType").focus();
		alert('Please select a PIV Type.');
		return false;
	}
	if(document.getElementById("txtIdNo").value.length==0)
	{
		document.getElementById("txtIdNo").focus();
		alert('Please enter Payee\'s  ID number.');
		return false;
	}
	if(document.getElementById("txtName").value.length==0)
	{
		document.getElementById("txtName").focus();
		alert('Please enter Payee\'s  Name.');
		return false;
	}
	if(document.getElementById("txtAddress").value.length==0)
	{
		document.getElementById("txtAddress").focus();
		alert('Please enter Payee\'s  Address.');
		return false;
	}
	if(document.getElementById("txtTelephone").value.length==0)
	{
		document.getElementById("txtTelephone").focus();
		alert('Please enter Payee\'s  Telephone.');
		return false;
	}
	if(document.getElementById("txtReperesntId").value.length==0)
	{
		document.getElementById("txtReperesntId").focus();
		alert('Please enter Representative ID.');
		return false;
	}
	if(document.getElementById("txtRepresentName").value.length==0)
	{
		document.getElementById("txtRepresentName").focus();
		alert('Please enter Representative Name.');
		return false;
	}
	if(document.getElementById("pivTotalTxt").value.length==0)
	{
		//document.getElementById("pivTotalTxt").focus();
		
		alert('Please enter PIV Amounts.');
		return false;
	}
	else
	{
		var tot = document.getElementById("pivTotalTxt").value;
		tot = parseFloat(tot.replace(/[^\d\.\-\ ]/g,""));
		//alert(tot);
		if(tot==0)
		{
			alert('Please enter PIV Amounts.');
			return false;
		}
	}
	return true;
}