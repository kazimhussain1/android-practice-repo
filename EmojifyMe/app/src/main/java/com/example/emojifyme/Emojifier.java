package com.example.emojifyme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;


public class Emojifier {

    private static final float EMOJI_SCALE_FACTOR = 0.9f;

    private static FaceDetector myDetector = null;


    public static Bitmap detectFacesAndOverlayEmoji(Context myContext, Bitmap myBitmap){

        myDetector = new FaceDetector.Builder(myContext)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        Frame myFrame = new Frame.Builder().setBitmap(myBitmap).build();



        int count = 0;
        if (myDetector.isOperational()) {
            SparseArray<Face> faces = myDetector.detect(myFrame);

            for (int i = 0; i<faces.size();i++){
                Face face = faces.get(i);
                if (face != null) {
                    Bitmap emoji = null;
                    switch (whichEmoji(faces.get(i))){

                        case FROWN:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.frown);
                            break;
                        case SMILE:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.smile);
                            break;
                        case LEFT_WINK:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.leftwink);
                            break;
                        case RIGHT_WINK:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.rightwink);
                            break;
                        case LEFT_WINK_FROWN:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.leftwinkfrown);
                            break;
                        case RIGHT_WINK_FROWN:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.rightwinkfrown);
                            break;
                        case EYES_CLOSED_FROWN:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.closed_frown);
                            break;
                        case EYES_CLOSED_SMILE:
                            emoji = BitmapFactory.decodeResource(myContext.getResources(),R.drawable.closed_smile);
                            break;
                    }

                    
                    myBitmap = addBitmapToFace(myBitmap,emoji,face);

                }
            }
            count = faces.size();

        }else {
            count = 0;
        }

        String  myString;
        if (count == 1){
            myString = "There is " + count + " face";
        }else {
            myString = "There are " + count + " faces";
        }

        Toast.makeText(myContext,myString,Toast.LENGTH_LONG).show();

        return myBitmap;

    }

    public static Emoji whichEmoji(Face oneFace){

        float left = oneFace.getIsLeftEyeOpenProbability();
        float right = oneFace.getIsRightEyeOpenProbability();
        float smile = oneFace.getIsSmilingProbability();


        boolean isSmiling = smile > 0.4;
        boolean isLeftEyeOpened = left > 0.4;
        boolean isRightEyeOpened = right > 0.4;

        Emoji emoji = null;

        if (isSmiling){
            if (isLeftEyeOpened && isRightEyeOpened){

                emoji = Emoji.SMILE;

            }else if (!isLeftEyeOpened && !isRightEyeOpened){

                emoji = Emoji.EYES_CLOSED_SMILE;

            }else  if(!isLeftEyeOpened && isRightEyeOpened){

                emoji = Emoji.LEFT_WINK;

            }else  if(isLeftEyeOpened && !isRightEyeOpened){

                emoji = Emoji.RIGHT_WINK;
            }
        }else {
            if (isLeftEyeOpened && isRightEyeOpened){

                emoji = Emoji.FROWN;

            }else if (!isLeftEyeOpened && !isRightEyeOpened){

                emoji = Emoji.EYES_CLOSED_FROWN;

            }else  if(!isLeftEyeOpened && isRightEyeOpened){

                emoji = Emoji.LEFT_WINK_FROWN;

            }else  if(isLeftEyeOpened && !isRightEyeOpened){

                emoji = Emoji.RIGHT_WINK_FROWN;

            }
        }



        Log.e("Left Eye:", String.valueOf(left));
        Log.e("Right Eye:", String.valueOf(right));
        Log.e("Smile:", String.valueOf(smile));

        return emoji;

    }

    /**
     * Combines the original picture with the emoji bitmaps
     *
     * @param backgroundBitmap The original picture
     * @param emojiBitmap      The chosen emoji
     * @param face             The detected face
     * @return The final bitmap, including the emojis over the faces
     */
    private static Bitmap addBitmapToFace(Bitmap backgroundBitmap, Bitmap emojiBitmap, Face face) {

        // Initialize the results bitmap to be a mutable copy of the original image
        Bitmap resultBitmap = Bitmap.createBitmap(backgroundBitmap.getWidth(),
                backgroundBitmap.getHeight(), backgroundBitmap.getConfig());

        // Scale the emoji so it looks better on the face
        float scaleFactor =  EMOJI_SCALE_FACTOR;

        // Determine the size of the emoji to match the width of the face and preserve aspect ratio
        int newEmojiWidth = (int) (face.getWidth() * scaleFactor);
        int newEmojiHeight = (int) (emojiBitmap.getHeight() *
                newEmojiWidth / emojiBitmap.getWidth() * scaleFactor);


        // Scale the emoji
        emojiBitmap = Bitmap.createScaledBitmap(emojiBitmap, newEmojiWidth, newEmojiHeight, false);

        // Determine the emoji position so it best lines up with the face
        float emojiPositionX =
                (face.getPosition().x + face.getWidth() / 2) - emojiBitmap.getWidth() / 2;
        float emojiPositionY =
                (face.getPosition().y + face.getHeight() / 2) - emojiBitmap.getHeight() / 3;

        // Create the canvas and draw the bitmaps to it
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);
        canvas.drawBitmap(emojiBitmap, emojiPositionX, emojiPositionY, null);

        return resultBitmap;
    }

    public static void releaseDetector(){

        if (myDetector != null){

            myDetector.release();
        }
    }

    private enum Emoji{

        SMILE,
        FROWN,
        LEFT_WINK,
        RIGHT_WINK,
        LEFT_WINK_FROWN,
        RIGHT_WINK_FROWN,
        EYES_CLOSED_SMILE,
        EYES_CLOSED_FROWN
    }

}
