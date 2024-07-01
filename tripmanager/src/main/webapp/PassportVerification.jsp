<!DOCTYPE html>
<html lang="en">
  
<style>
  form {
      display: flex;
      flex-direction: column;
  }

  form label {
      margin-top: 10px;
  }

  form input {
      padding: 10px;
      margin-top: 5px;
      font-size: 16px;
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


  #Uploads{

    padding-left: 90px;
  }
</style>
</head>
<body>

<div id="loginModal" class="modal">
  <div class="modal-content">
    <h2 style="text-align: center;">Passport Verification</h2>
    <h3 style="text-align: center;font-weight: 100;">I hearby accepting terms and condition to use my data and Passport details for further booking process.</h3>
    <div class="choose">
        <form action="PassportVerification" method="post" enctype="multipart/form-data">
        <input id="Uploads"  type="file" name="imgfile">
        <br><br>
        <input  type="submit" value="Upload">
        </form>
    </div>
  </div>
</div>


</body>

</html>