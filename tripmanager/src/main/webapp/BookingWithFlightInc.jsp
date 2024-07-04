<!DOCTYPE html>
<html lang="en">
<head>
  <style>
    form {
        display: flex;
        flex-direction: column;
    }
    /* form label {
        margin-top: 10px;
    } */
    form-group input, form select {
        padding: 10px;
        margin-top: 5px;
        font-size: 16px;
        width: 100%;
        box-sizing: border-box;
    }
    form button {
        padding: 10px;
        margin-top: 20px;
        font-size: 16px;
        cursor: pointer;
    }
    .modal {
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
    }
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 400px;
    }
    .form-control {
        width: 94%;
    }
  </style>
</head>
<body>

<%
    String totalAmount = (String) session.getAttribute("packagePrice");
%>

<div id="loginModal" class="modal">
    <div class="modal-content">
        <form id="dynamicForm" action="AddUser" method="post">
            <b style="font-weight: 200; text-align: center;">
                <%= totalAmount %> is your total amount which includes hotel and site-seeing and includes flight charges.
            </b>
            
            <div class="form-group">
                <input type="text" name="textBox[]" class="form-control" placeholder="Name">
            </div>
    
            <div class="form-group">
                <input type="number" name="age[]" class="form-control" placeholder="Age">
            </div>
            
            <div class="form-group">
                <label>Gender:</label>
                <input type="radio" name="gender[0][]" value="male"> Male
                <input type="radio" name="gender[0][]" value="female"> Female
            </div>
            
            <button type="button" class="add-button" onclick="addTextBox()">Add Another Traveller</button>
            <br><br>
            
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

<script>
var travelerCount = 1; 

function addTextBox() {
    var form = document.getElementById("dynamicForm");
    
    var newDiv = document.createElement("div");
    newDiv.className = "form-group";
    
    var newNameInput = document.createElement("input");
    newNameInput.type = "text";
    newNameInput.name = "textBox[]";
    newNameInput.className = "form-control";
    newNameInput.placeholder = "Name";
    
    var newAgeInput = document.createElement("input");
    newAgeInput.type = "number";
    newAgeInput.name = "age[]";
    newAgeInput.className = "form-control";
    newAgeInput.placeholder = "Age";
    
    var genderLabel = document.createElement("label");
    genderLabel.textContent = "Gender:";
    
    var maleRadio = document.createElement("input");
    maleRadio.type = "radio";
    maleRadio.name = "gender[" + travelerCount + "][]"; 
    maleRadio.value = "male";
    
    var maleLabel = document.createElement("label");
    maleLabel.textContent = "Male";
    maleLabel.appendChild(maleRadio);
    
    var femaleRadio = document.createElement("input");
    femaleRadio.type = "radio";
    femaleRadio.name = "gender[" + travelerCount + "][]"; 
    femaleRadio.value = "female";
    
    var femaleLabel = document.createElement("label");
    femaleLabel.textContent = "Female";
    femaleLabel.appendChild(femaleRadio);
    
    newDiv.appendChild(newNameInput);
    newDiv.appendChild(newAgeInput);
    newDiv.appendChild(genderLabel);
    newDiv.appendChild(maleLabel);
    newDiv.appendChild(femaleLabel);
    
    form.appendChild(newDiv);
    
    travelerCount++; 
}
</script>

</body>
</html>
