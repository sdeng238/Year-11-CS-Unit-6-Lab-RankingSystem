public class User {
    //start at -8 to 8
    //no 0 rank (-1 -> 1)
    int rank;
    int progress;

    public User() {
        rank = -8;
        progress = 0;
    }

    public int getRank() {
        return rank;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return "User{" +
                "rank=" + rank +
                ", progress=" + progress +
                '}';
    }

    public void incProgress(int activityRank)
    {
        //throw exception if activity rank <-8, =0 or >8
        if(activityRank < -8 || activityRank == 0 || activityRank > 8)
        {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }

        //check if zero exists between user's rank and activity's rank
        boolean zeroExists = false;
        int difference;
        //if user's rank is higher than activity's, check whether range includes 0
        if(activityRank < rank)
        {
            //check if range between user's rank and activity's rank has 0
            for(int num = activityRank; num <= rank; num++)
            {
                if(num == 0)
                {
                    zeroExists = true;
                }
            }

            if(zeroExists)
            {
                difference = rank - activityRank - 1;
            }
            else
            {
                difference = rank - activityRank;
            }

            if(difference == 1)
            {
                progress++;
            }
        }
        //if user's rank is equal to activity's rank, add 3 points
        else if(rank == activityRank)
        {
            progress = progress + 3;
        }
        //if user's rank is lower than activity's rank
        else if(activityRank > rank)
        {
            //check if range between user's rank and activity's rank has 0
            for(int num = rank; num <= activityRank; num++)
            {
                if(num == 0)
                {
                    zeroExists = true;
                }
            }

            //if have 0, subtract 1 from the difference between activity's rank and user's rank when adding points
            if(zeroExists)
            {
                progress = progress + 10 * (activityRank - rank - 1) * (activityRank - rank - 1);
            }
            else
            {
                progress = progress + 10 * (activityRank - rank) * (activityRank - rank);
            }
        }

        //if the updated progress is larger than or equals to 100 points
        if(progress >= 100)
        {

            //increase the user's rank by the number of multiples of 100 subtracted from the updated progress
            rank = rank + (progress / 100);
            //edit the progress points to the difference between the updated progress and the nearest smaller multiple of 100
            progress = progress - 100 * (progress / 100);
            //if the user's updated rank is 0, increase to 1
            if(rank == 0)
            {
                rank++;
            }
            //if the user's updated rank is larger than 8, subtract 1 from it until it reaches 8
            else if(rank > 8)
            {
                while(rank > 8)
                {
                    rank--;
                }
            }
        }
        //if the updated progress is negative
        else if(progress < 0)
        {
            //edit the progress points to the sum of the negative updated progress and the nearest larger multiple of 100
            progress = 100 * (-(progress / 100 - 1)) + progress;
            //decrease the user's rank by the number of multiples of 100 increased to the negative updated progress
            rank = rank + (progress / 100 - 1);
            //if the user's updated rank is 0, decrease to -1
            if(rank == 0)
            {
                rank--;
            }
            //if the user's updated rank is smaller than -8, add 1 to it until it reaches -8
            else if(rank < -8)
            {
                while(rank < -8)
                {
                    rank++;
                }
            }
        }
    }
}
