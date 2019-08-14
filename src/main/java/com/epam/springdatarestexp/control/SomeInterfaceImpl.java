package com.epam.springdatarestexp.control;

import org.springframework.stereotype.Service;

@Service
public class SomeInterfaceImpl implements SomeInterface { // Cheching how @RequiredArgsConstructor works on interface. There should be bean of class that implements this interface
    @Override
    public void check() {
        System.out.println("---SomeInterfaceImpl---");
    }
}
