    package com.example.remotedatabase.repository

    import MahasiswaService
    import com.example.remotedatabase.model.Mahasiswa
    import okio.IOException

    interface MahasiswaRepository{
        suspend fun getMahasiswa():List<Mahasiswa>

        suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

        suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

        suspend fun deleteMahasiswa(nim: String)

        suspend fun getMahasiswaById(nim: String): Mahasiswa
    }

    class NetworkMahasiswaRepository(
        private val mahasiswaService: MahasiswaService
    ): MahasiswaRepository {
        override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
            mahasiswaService.insertMahasiswa(mahasiswa)
        }

        override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
            mahasiswaService.updateMahasiswa(nim, mahasiswa)
        }

        override suspend fun deleteMahasiswa(nim: String) {
            try {
                val response = mahasiswaService.deleteMahasiswa(nim)

                if (!response.isSuccessful) {
                    throw IOException("Failed to delete mahasiswa. HTTP status code: " +
                            "${response.code()}")
                } else {
                    response.message()
                    println(response.message())
                }
            } catch (e: Exception) {
                throw e
            }
        }

        override suspend fun getMahasiswa(): List<Mahasiswa> = mahasiswaService.getMahasiswa()

        override suspend fun getMahasiswaById(nim:String): Mahasiswa{
            return mahasiswaService.getMahasiswaById(nim)
        }
    }