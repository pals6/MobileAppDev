package com.assignment.myapplication;

public class FruitsInfo {
    private int imageViewFruitsId;
    private int selectableFruitIds;
    private boolean isSelected=false;
    private boolean isMatched=false;

    public FruitsInfo(int imageViewFruitsId, int selectableFruitIds) {
        this.imageViewFruitsId = imageViewFruitsId;
        this.selectableFruitIds = selectableFruitIds;
    }

    public int getImageViewFruitsId() {
        return imageViewFruitsId;
    }

    public void setImageViewFruitsId(int imageViewFruitsId) {
        this.imageViewFruitsId = imageViewFruitsId;
    }

    public int getSelectableFruitIds() {
        return selectableFruitIds;
    }

    public void setSelectableFruitIds(int selectableFruitIds) {
        this.selectableFruitIds = selectableFruitIds;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    @Override
    public String toString() {
        return "FruitsInfo{" +
                "imageViewId=" + imageViewFruitsId +
                ", drawableId=" + selectableFruitIds +
                ", isSelected=" + isSelected +
                ", isMatched=" + isMatched +
                '}';
    }
}
