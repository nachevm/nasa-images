# NASA images API

API that can be used to display daily images from the rovers that are on Mars. By default, gets the navigation camera images from the
curiosity rover from the last 10 days and limits the number of images to 3 per day.

## **Pre-requisites**

Java 11+

### **Lombok**

Since Lombok is used, you need to install the appropriate plugin within your IDE (https://projectlombok.org)

### **Code Style**

For those using Intellij IDEA there is already a formatter included in the Project. If it's not enabled by default you can do that in
File->Settings->Editor->Code Style-Scheme->Project. Also make sure that "Enable EditorConfig support" is checked, this can be found in
File->Settings->Editor->Code Style

## **How to use**

```./gradlew console-app:run```
