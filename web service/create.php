<?php
require("koneksi.php");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $email = $_POST["email"];
    $password = $_POST["password"];
    $nama = $_POST["nama"];

    $perintah = "INSERT INTO user_detail (email, password, nama, level) VALUES('$email','$password','$nama','1')";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek      = mysqli_affected_rows($konek);

    if ($cek > 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Simpan Data Berhasil";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menyimpan Data";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);
