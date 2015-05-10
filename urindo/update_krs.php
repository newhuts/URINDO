  <?php
// array for JSON response
$response = array();
// check for required fields
if (isset($_GET['id']) && isset($_GET['npm'])) { 
    $id = $_GET['id'];
    $npm = $_GET['npm'];
    
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql inserting a new row
     $result = mysql_query("UPDATE krs SET status = 1 WHERE id = '$id' AND npm = '$npm'");
     $result = mysql_query("UPDATE mahasiswa SET krs = 2 WHERE npm = '$npm'");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "krs berhasil diterima.";
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Ohh!. ada yang salah.";
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