package com.example.remotedatabase.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Mahasiswa (
    val nim: String,
    val nama: String,
    val alamat: String,

    @SerialName("jenis_kelamin")
    val jenisKelamin: String,

    val kelas: String,
    val angkatan: String
)
