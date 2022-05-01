import model.Producto

class Cliente(val cinta:Cinta, val nombre:String):Thread() {

    private var productos = arrayListOf<Producto>()
    var terminado = false

    init {
        val random = ((Math.random()*10)+3).toInt()
        for (i in 0..random){
            productos.add(
                Producto(
                "producto $i",
                ((Math.random()*5000)+1000).toLong())
            )
        }
    }

    override fun run() {
        for (producto in productos){
            println("$nombre pone nuevo producto en la cinta")
            cinta.put(nombre,producto)
        }
        terminado = true
        println("$nombre se ha quedado sin productos")
    }
}