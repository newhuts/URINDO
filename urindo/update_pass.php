  <?php
// array for JSON response
$response = array();
// check for required fields
if (isset($_GET['npm']) && isset($_GET['pass'])) { 
    $npm = $_GET['npm'];
    $pass = $_GET['pass'];
    $npass = $_GET['newpass'];
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql inserting a new row
     $result = mysql_query("UPDATE mahasiswa SET password = '$npass' WHERE npm = '$npm'and password = '$pass' ");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Password berhasil diganti.";
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Ohh!. password lama salah.";
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Field tidak lengkap";
    // echoing JSON response
    echo json_encode($response);
}
?>