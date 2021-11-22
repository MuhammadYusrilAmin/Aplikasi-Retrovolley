<?php
require("koneksi.php");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $id = $_POST["id"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $nama = $_POST["nama"];

    $perintah = "UPDATE user_detail SET email = '$email', password = '$password', nama = '$nama' WHERE id = '$id'";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek      = mysqli_affected_rows($konek);

    if ($cek > 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Diubah";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Data Gagal Diubah";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);
