  <?php
// array for JSON response
$response = array();
// check for required fields
if (isset($_GET['npm']) && isset($_GET['kodemk']) && isset($_GET['nilai'])) { 
    $npm = $_GET['npm'];
    $kodemk = $_GET['kodemk'];
    $nilai = $_GET['nilai'];
    $nkelas = $_GET['naikkelas'];
    $smt = $_GET['smt'];
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
    // connecting to db
    $db = new DB_CONNECT();
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO khs(npm, kodemk, smt, nilai) VALUES('$npm', '$kodemk', '$smt', '$nilai')");
    $result = mysql_query("UPDATE krs SET status = 2 WHERE npm = '$npm'");
    $result = mysql_query("UPDATE mahasiswa SET krs = 0, smt = '$nkelas', totalkrs = 0 WHERE npm = '$npm'");
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "khs berhasil di input.";
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
    $response["message"] = "Field tidak lengkap nilai";
    // echoing JSON response
    echo json_encode($response);
}
?>