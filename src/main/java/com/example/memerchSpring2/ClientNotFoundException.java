package com.example.memerchSpring2;

class ClientNotFoundException extends RuntimeException {
    ClientNotFoundException(Integer clientID) {
        super("Could not find client with clientID=" + clientID);
    }
}
