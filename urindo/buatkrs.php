  <?php
// array for JSON response
$response = array();
// check for required fields
if (isset($_GET['npm']) && isset($_GET['kodemk'])) { 
    $npm = $_GET['npm'];
    $kodemk = $_GET['kodemk'];
    $totkrs = $_GET['totalkrs'];
    $smtkrs = $_GET['smt'];
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO krs(npm, kodemk, smt) VALUES('$npm', '$kodemk', '$smtkrs')");
     $result = mysql_query("UPDATE mahasiswa SET krs = 1 WHERE npm = '$npm'");
      $result = mysql_query("UPDATE mahasiswa SET totalkrs = '$totkrs' WHERE npm = '$npm'");
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "permintaan berhasil dibuat.";
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