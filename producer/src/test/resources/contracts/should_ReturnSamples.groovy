package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders

Contract.make {

    description("Should return samples...")

    request {
        method("GET")
        url("/sample")
    }

    response {
        status(200)
        headers {
            contentType("application/json")
        }
        body([[name: "Raul","age": 30],[name: "Ana", age: 30],[ name: "Henrique", age: 4]])
    }

}