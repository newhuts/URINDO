<?php
// array for JSON response
$response = array();
// include db connect class
require_once __DIR__ . '/db_connect.php';
// connecting to db
$db = new DB_CONNECT();
$npm = $_GET['npm'];
// get all products from products table
$result = mysql_query("SELECT * FROM khs, matkul WHERE matkul.kode_mk = khs.kodemk AND khs.npm = '$npm' GROUP BY matkul.smt ORDER BY khs.id ASC") or die(mysql_error());
// SELECT *, sum(matkul.sks) FROM krs, mahasiswa, matkul where matkul.kode_mk = krs.kodemk and krs.npm = mahasiswa.npm and status = 0 GROUP BY mahasiswa.npm ORDER BY krs.id ASC
// SELECT * FROM krs, mahasiswa where krs.npm = mahasiswa.npm and status = 0 GROUP BY mahasiswa.npm ORDER BY id ASC

if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["khs"] = array();
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $terima = array();
            $terima["smt"] = $row["smt"];

        // push single product into final response array
        array_push($response["khs"], $terima);
    }
    // success
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "tidak ada permintaan";
    // echo no users JSON
    echo json_encode($response);
}
?>
