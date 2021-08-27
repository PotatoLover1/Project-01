/**
 * 
 */
let form = document.getElementById("login").addEventListener('submit', login);

async function login(e){
	e.preventDefault();
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;
	
	let user = {
		username,
		password
	}
	
	console.log(user);
	
	
	try{
		console.log("js try block1");
		let req = await fetch('http://localhost:8080/Ers1Project/api/login', 
						{
							method: 'POST',
							headers: {
							'Content-Type': 'application/json'
							},
						body: JSON.stringify(user)
	});
		let res = await req.json();
		console.log("js try block2")
	} catch(e){
		console.log(e);
		alert('Username or password incorrect!');
		return;
	}
	location.href = 'resources/html/Reimbursement.html';
}