import main.kotlin.Webshop
import main.kotlin.animal.Dog
import main.kotlin.animal.Gender
import org.junit.jupiter.api.Test
import stubs.WebshopObservorStub

internal class WebshopTest {

    @Test
    fun `Webshop observor update triggered when product list changes`() {
        val webshop = Webshop()
        val webshopObservor= WebshopObservorStub()
        webshop.observe(webshopObservor)
        assert(!webshopObservor.observorTriggered)
        webshop.addProduct(Dog("TestDog", Gender.Male))
        assert(webshopObservor.observorTriggered)
    }
}