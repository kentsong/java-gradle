/**
 * ad.vast 配置文件
 * Created by songzhukai on 2020-03-26.
 */
public class BootAdVO {
    // N秒跳过
    private String skipTime = "";
    // 动画缩小位置
    private String position = "";
    // mp4、zip (目前只支援mp4)
    private String materialType = "";

    public int getSkipTime() {
        try {
            return Integer.parseInt(skipTime);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "BootAdVO{" +
                "skipTime='" + skipTime + '\'' +
                ", position='" + position + '\'' +
                ", materialType='" + materialType + '\'' +
                '}';
    }

    public void setSkipTime(String skipTime) {
        this.skipTime = skipTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public class PositionData {
        private String x = "";
        private String y = "";
        private String width = "";
        private String height = "";

        public float getX() {
            try {
                return Float.parseFloat(x);
            } catch (Exception e) {
                return 0f;
            }
        }

        public void setX(String x) {
            this.x = x;
        }

        public float getY() {
            try {
                return Float.parseFloat(y);
            } catch (Exception e) {
                return 0f;
            }
        }

        public void setY(String y) {
            this.y = y;
        }

        public float getWidth() {
            try {
                return Float.parseFloat(width);
            } catch (Exception e) {
                return 0f;
            }
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public float getHeight() {
            try {
                return Float.parseFloat(height);
            } catch (Exception e) {
                return 0f;
            }
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }


}
