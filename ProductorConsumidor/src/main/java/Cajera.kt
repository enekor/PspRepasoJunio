class Cajera(
    val cinta: Cinta,
    val nombre:String,
    val cliente: Cliente
    ):Thread() {

    var terminado = false

    override fun run() {
        while(true){
            var sleep = cinta.read(nombre)
            sleep(sleep)
        }
    }
}