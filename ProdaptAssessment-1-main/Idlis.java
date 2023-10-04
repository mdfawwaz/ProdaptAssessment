public static boolean canMake(int small, int big, int goal) 
{
    
    int smallIdlis;
    int bigIdlis;
    int remainingIdlis;
    
    bigIdlis = big * 500;
    smallIdlis = small * 100;
    
    if(smallIdlis + bigIdlis < goal) {
        return false;
    }
    if(goal % 500 <= smallIdlis){
        return true;
    }
    else {
        return false;
    }
}