var access_token = "";
var account_id = "";
var uploadFilePath = "";

window.onload = customize;

function customize(){

	window.document.getElementById('b3').disabled = true;
	window.document.getElementById('b4').disabled = true;
	window.document.getElementById('b5').disabled = true;
	window.document.getElementById('b6').disabled = true;
	
	window.document.getElementById('div8').style.display = 'none';
	window.document.getElementById('div10').style.display = 'none';
	
	if(window.location.href.substring(35).length > 0){
		window.document.getElementById('b1').disabled = true;
		window.document.getElementById('b2').disabled = false;

	} else {
		
		window.document.getElementById('b2').disabled = true;
		window.document.getElementById('b1').disabled = false;
		
	}

}

function doQuery()
{
	var q_str = 'reqType=doQuery';
	doAjax('DBoxClientMediator',q_str,'doQuery_back','post',0);
}

function doQuery_back(result)
{

   window.location = result;


}

function doQuery1()
{
	var q_str = 'reqType=doQuery1';

    if(window.location.href.substring(41).length>0){
	q_str = q_str+'&code='+window.location.href.substring(41);
	doAjax('DBoxClientMediator',q_str,'doQuery1_back','post',0);
    }else alert('Please connect first...');
}

function doQuery1_back(result)
{
  	var json = result;
  	json_obj = JSON.parse(json);

  	access_token = json_obj.access_token;
  	account_id = json_obj.account_id;
    var html_cont=""; 
   	for (var x in json_obj) {
   		if (json_obj.hasOwnProperty(x)) {
   			html_cont=html_cont+"<tr><td align='right'><i>"+x+"</i>:</td><td>&nbsp</td><td align='left'>"+json_obj[x]+"</td></tr>";	
   		}
   	}
   	
   	html_cont="<table>"+html_cont+"<table>";
    window.document.getElementById('div3').innerHTML=html_cont;
    
    
    window.document.getElementById('b2').disabled = true;
	window.document.getElementById('b3').disabled = false;
	window.document.getElementById('b6').disabled = false;

}


function doQuery2()
{
	
	window.document.getElementById('div3').style.display = 'none';
//	window.document.getElementById('div3').style.visibility = 'hidden';
	var q_str = 'reqType=doQuery2';
	q_str = q_str+'&access_token='+access_token;
	q_str = q_str+'&account_id='+account_id;
	doAjax('DBoxClientMediator',q_str,'doQuery2_back','post',0);
	
}

function doQuery2_back(result)
{

  	var json = result;
  	json_obj = JSON.parse(json);
  	var html_cont=""; 
  	
//alert(json_obj.access_token);
//  	access_token = json_obj.access_token;
  	html_cont = fromObjToTable(json_obj);
     
    window.document.getElementById('div5').innerHTML=html_cont;
    
    
    window.document.getElementById('b3').disabled = true;
    window.document.getElementById('b4').disabled = false;
}

function fromObjToTable(obj){
	
	var html_cont=""; 
   	for (var x in obj) {
   		if (obj.hasOwnProperty(x)) {
   			
   			if (obj[x] instanceof Object){
   				html_cont=html_cont+"<tr><td align='right'><i>"+x+"</i>:</td><td>&nbsp</td><td align='left'>"+fromObjToTable(obj[x])+"</td></tr>";	
   			}else{
   				html_cont=html_cont+"<tr><td align='right'><i>"+x+"</i>:</td><td>&nbsp</td><td align='left'>"+obj[x]+"</td></tr>";	
   			}
   		}
   	}
   	html_cont="<table>"+html_cont+"</table>";
	
	
	return html_cont;
}

function doQuery3_()
{
//alert('doQuery3_...');


	
	window.document.getElementById('div5').style.display = 'none';
//	window.document.getElementById('div5').style.visibility = 'hidden';
    
	uploadFilePath = "images/image_initial.png";
	var html_cont="<table><tr><td><table><tr><td align='left'><i>Initial image</i>: "+uploadFilePath+"</td></tr><tr><td align='left'><img src='"+uploadFilePath+"' style='width:200px;height:200px;'></td></tr></table></td><td><table><tr><td></td></tr><tr><td></td></tr></table></td></tr></table>"; 
  	window.document.getElementById('div7').innerHTML=html_cont;
  	window.document.getElementById('b4').disabled = true;
  	window.document.getElementById('div6').style.display = 'none';
	window.document.getElementById('div8').style.display = 'block';
    window.document.getElementById('b5').disabled = false;
			
}

function doQuery3()
{
//alert('doQuery3...');


	
	var q_str = 'reqType=doQuery3';
	q_str = q_str+'&access_token='+access_token;
	q_str = q_str+'&filePath='+uploadFilePath;
	doAjax('DBoxClientMediator',q_str,'doQuery3_back','post',0);
	
	
	
	
	
}

function doQuery3_back(result)
{

//alert('result3:'+result);


	
  	var json = result;
  	json_obj = JSON.parse(json);
  	var html_c=""; 
  	html_c = fromObjToTable(json_obj);
  	
//  	var html_cont="<table><tr><tb><table><tr><td align='left'><i>Initial image</i>: "+uploadFilePath+"</td></tr><tr><td align='left'><img src='"+uploadFilePath+"' style='width:200px;height:200px;'></td></tr></table></td><td>"+html_c+"</td></tr></table>"; 
  	var html_cont="<table><tr><td><table><tr><td align='left'><i>Initial image</i>: "+uploadFilePath+"</td></tr><tr><td align='left'><img src='"+uploadFilePath+"' style='width:200px;height:200px;'></td></tr></table></td><td><table><tr><td><i>Uploaded image</i>: image_initial_uploaded.png</td></tr><tr><td>"+html_c+"</td></tr></table></td></tr></table>"; 
    window.document.getElementById('div7').innerHTML=html_cont;
    
    
    window.document.getElementById('b5').disabled = true;
    
	
    

}

function doQuery4()
{
//alert('doQuery4...');

	var q_str = 'reqType=doQuery4';
	q_str = q_str+'&access_token='+access_token;
	doAjax('DBoxClientMediator',q_str,'doQuery4_back','post',0);	
	
}

function doQuery4_back(result)
{

  	var json = result;
  	json_obj = JSON.parse(json);
  	var html_cont=""; 
  	
//alert(json_obj.access_token);
//  	access_token = json_obj.access_token;
  	html_cont = fromObjToTable(json_obj);
     
    window.document.getElementById('div10').innerHTML=html_cont;
    
}