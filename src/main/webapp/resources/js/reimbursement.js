/**
 */

let container = document.getElementById('reimbursement-container');

toggle_visible();

async function getReimbs(){
	let res = await fetch('http://localhost:8080/Ers1Project/api/Reimbursement');
	let data = await res.json();
	console.log(data);
	populateReimbs(data);
}

function populateReimbs(data){
	for(ReimbObj of data){
		let reimb = document.createElement('div');
		let rd = ReimbObj.reimbursement_resolve;
		let ur = ReimbObj.resolved;
		if(ur==null){
			ur = null;
		}else{
			ur = ReimbObj.resolved.username;
		}
		if(rd == null){
			rd = null;
		}else{
			rd = new Date(ReimbObj.reimbursement_resolve);
		}
		reimb.innerHTML = '<p> Reimbursement Id: '+ReimbObj.reimb_id + '<br/>Amount: '+ReimbObj.reimbursement_amount+'<br/>Type: '+ReimbObj.type_id.type+
		'<br/>Description: '+ReimbObj.reimbursment_description+'<br/>Submitted by: '+ReimbObj.submited.username+'<br/>Submitted on: '+ new Date(ReimbObj.reimbursement_submit) +
		'<br/>Status: '+ReimbObj.status_id.status+'<br>Resolved by: '+ ur + '<br>Date Resloved: '+ rd +'<p/>';
		console.log(reimb);
		container.append(reimb);
	}
}

let form = document.getElementById("request-form").addEventListener('submit', newReimb);

async function newReimb(e){
	e.preventDefault();
	/*let req = await fetch('http://localhost:8080/Project1/api/session');
	let res = await req.json();
	let id = res.userId;*/
	let type = document.getElementById("type").value;
	let description = document.getElementById("description").value;
	let amount = document.getElementById("amount").value;
	
	let reimb = {
		type: type,
		description: description,
		amount: amount
	}
	
	await fetch('http://localhost:8080/Ers1Project/api/Reimbursement', {
		method: "POST",
		contentType: "application/json",
		body: JSON.stringify(reimb)
	});
	
	req = await fetch('http://localhost:8080/Ers1Project/api/Reimbursement');
	let data = await req.json();
	container.innerHTML ='';
	populateReimbs(data);
	
}
let showbutton = document.getElementById("form-button").addEventListener("click",toggle_visible);

function toggle_visible(){
	let frm = document.getElementById("reimbursement-form");
	if(frm.style.display == 'block'){
		frm.style.display = 'none';
	}else{
		frm.style.display = 'block';
	}
}


getReimbs();