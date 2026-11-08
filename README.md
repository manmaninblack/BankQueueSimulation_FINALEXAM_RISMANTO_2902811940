# BankQueueSimulation_FINALEXAM_RISMANTO_2902811940
Java Bank Queue Simulation using Circular Queue and Weighted Round-Robin scheduling to manage VIP and regular customers.
# Bank Queue Simulation

**Course:** Data Structures and Algorithm Analysis  
**Student:** Rismanto  
**Student ID:** 2902811940

## Project Description

This project implements a bank queue simulation using Circular Queue and Weighted Round-Robin scheduling to manage VIP and regular customers.

The system allows VIP customers to receive priority service while ensuring that regular customers do not experience starvation.

## Features

- Manual Circular Queue implementation using Java arrays.
- Separate queues for VIP and regular customers.
- VIP priority with a maximum of two consecutive VIP services.
- Regular customers receive their turn when they are waiting.
- Queue status display.
- Automatic ticket numbering.

## Data Structures and Algorithm

- **Circular Queue:** Stores customer data using a fixed-size array.
- **Weighted Round-Robin:** Controls service turns with a 2:1 VIP-to-regular ratio when both queues are waiting.

## Main Methods

- `enqueueVIP()`: Adds a VIP customer.
- `enqueueReguler()`: Adds a regular customer.
- `dequeue()`: Selects and removes the next customer to be served.
- `displayQueue()`: Displays the current queue status.

## How to Run

1. Download `BankQueueSimulation.java`.
2. Compile the program:

   ```bash
   javac BankQueueSimulation.java
   ```

3. Run the program:

   ```bash
   java BankQueueSimulation
   ```

## Expected Service Order

```text
VIP-01
VIP-02
REG-01
VIP-03
VIP-04
REG-02
REG-03
```

## Implementation Notes

The program uses native Java arrays and manually implemented queue operations. It does not use Java Collections Framework classes.
