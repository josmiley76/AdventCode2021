public class ChallengeTwo {

    private static int horizontalPosition = 0;
    private static int verticalPosition = 0;
    private static int aim = 0;

//    public static void calculatePosition(String position){
//        String[] positionAsWords = position.split(" ");
//        if(positionAsWords[0].equals("forward")){
//            horizontalPosition = horizontalPosition + Integer.parseInt(positionAsWords[1]);
//        }else{
//            verticalPosition = (positionAsWords[0].equals("up") ? verticalPosition - Integer.parseInt(positionAsWords[1]) :
//                                                                  verticalPosition + Integer.parseInt(positionAsWords[1]));
//        }
//    }
    public static void calculatePosition(String position){
        String[] positionAsWords = position.split(" ");

        if(positionAsWords[0].equals("forward")){
            int forwardValue = Integer.parseInt(positionAsWords[1]);
            horizontalPosition = horizontalPosition + forwardValue;
            verticalPosition = verticalPosition + forwardValue * aim;
        }else{
            setAim(positionAsWords[0], positionAsWords[1]);
        }
    }

    public static void setAim(String direction, String aimValueAsString){
        aim = (direction.equals("up") ? aim - Integer.parseInt(aimValueAsString) : aim + Integer.parseInt(aimValueAsString));
    }

    public static int getFinalSubmarinePosition(){
        System.out.println("horizontalPosition = " + horizontalPosition);
        System.out.println("verticalPosition = " + verticalPosition);
        return horizontalPosition * verticalPosition;
    }
}
