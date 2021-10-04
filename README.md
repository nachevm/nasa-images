# Description

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

For default configuration run: ```./gradlew console-app:run```
<br>
For advanced configuration run: ```./gradlew console-app:run --args='<command-name> -arg1 val1 -arg2 val2'```

<table>
    <thead>
        <tr>
            <th>Commands</th>
            <th>Arguments</th>
            <th>Default</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=4>mars-rover</td>
            <td>rover</td>
            <td>CURIOSITY</td>
        </tr>
        <tr>
            <td>camera</td>
            <td>NAVCAM</td>
        </tr>
        <tr>
            <td>days</td>
            <td>10</td>
        </tr>
        <tr>
            <td>imagesLimit</td>
            <td>3</td>
        </tr>
    </tbody>
</table>

Example: ```./gradlew console-app:run --args='mars-rover -days 1 -camera fhaz'```

NASA's documentation can be found [here](https://api.nasa.gov/) (Browse APIs / Mars Rover Photos).
