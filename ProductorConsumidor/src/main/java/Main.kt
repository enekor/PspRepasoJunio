fun main() {

    val cinta = Cinta()
    val cliente = Cliente(cinta,"Juan")
    val cajera = Cajera(cinta,"Alberta",cliente)

    cliente.start()
    cajera.start()
}