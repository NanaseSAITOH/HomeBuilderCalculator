package com.opengl.jackn.calculator;


        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.icu.math.BigDecimal;
        import android.icu.text.NumberFormat;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;


  public  class MainActivity extends Activity {

      java.math.BigDecimal number;    // 入力された数字
      java.math.BigDecimal result;    // 計算結果


      private int operatorId;    // 演算子のリソースID

      private int i=0;

      private String smallNumber;

      java.math.BigDecimal oldnumber;

      boolean FirstFlag=true;

      boolean FlagEqual=false;

      boolean FlagPoint=false;

      int smallKETA=1;

      /** Called when the activity is first created. */
      @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          setContentView(R.layout.activity_main);
          number=java.math.BigDecimal.valueOf(0);
          result=java.math.BigDecimal.valueOf(0);
          smallNumber="0.";
          Button buttonOver400=findViewById(R.id.Over400);
          Button buttonOver200=findViewById(R.id.Over200);
          Button buttonUpTo200=findViewById(R.id.UpTo200);
          Button buttonTubo=findViewById(R.id.tubo);
          Button buttonC=findViewById(R.id.CLEAR_BUTTON);
          Button buttonPoint = findViewById(R.id.point);
          buttonC.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  cKansuu();
              }
          });
          buttonOver400.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(number==java.math.BigDecimal.valueOf(0)) {
                      TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
                      resultView.setText("エラー");
                  }else{
                      number = (number.multiply(java.math.BigDecimal.valueOf(0.03)).add(java.math.BigDecimal.valueOf(60000)));
                      show(number);
                  }
              }
          });
          buttonOver200.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(number==java.math.BigDecimal.valueOf(0)) {
                      TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
                      resultView.setText("エラー");
                  }else{
                      number= (number.multiply(java.math.BigDecimal.valueOf(0.04)).add(java.math.BigDecimal.valueOf(20000)));
                      show(number);
                  }

              }
          });

          buttonUpTo200.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(number==java.math.BigDecimal.valueOf(0)) {
                      TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
                      resultView.setText("エラー");
                  }else{
                      number= (number.multiply(java.math.BigDecimal.valueOf(0.05)));
                      show(number);
                  }

              }
          });

          buttonTubo.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(number==java.math.BigDecimal.valueOf(0)) {
                      TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
                      resultView.setText("エラー");
                  }else{
                      number= (number.divide(java.math.BigDecimal.valueOf(3.30578), 2, BigDecimal.ROUND_DOWN));
                      show(number);
                  };
              }
          });
          buttonPoint.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  FlagPoint=true;
              }
          });
      }

      /**
       * 数字のボタンがクリックされたときのハンドラー。
       * @param view
       */
      public void onNumberButtonClick(View view) {
          FlagEqual=true;
          Button button = (Button) view;
          if(FlagPoint==false) {
              number = number.multiply(java.math.BigDecimal.valueOf(10)) ;
              number = number.add(java.math.BigDecimal.valueOf(Integer.parseInt(button.getText().toString())));
              oldnumber=number;
              show(number);
          }else if(FlagPoint==true){
              smallNumber=smallNumber+button.getText().toString();
              //smallNumber = (smallNumber.add(big.multiply(java.math.BigDecimal.valueOf(Math.pow(10,-smallKETA)))));
              java.math.BigDecimal big=new java.math.BigDecimal(smallNumber);
              number=oldnumber.add(big);
              //smallKETA++;
              show(number);
          }
      }

      /**
       * 演算子のボタンがクリックされたときのハンドラー。
       * @param view
       */
      public void onOperatorButtonClick(View view) {

          if(FirstFlag==true) {
              operatorId = view.getId();
              result = number;
              number = java.math.BigDecimal.valueOf(0);
              smallNumber= "0.";
              oldnumber= java.math.BigDecimal.valueOf(0);
              FirstFlag=false;
              System.out.println(true);
          }else{
              Equal();
              operatorId = view.getId();
              result = number;
              number = java.math.BigDecimal.valueOf(0);
              System.out.println(false);
          }
          smallKETA=1;
          FlagPoint=false;
      }

      /**
       * 「=」ボタンがクリックされたときのハンドラー。
       * @param view
       */
      public void onEqualButtonClick(View view) {
          switch (operatorId) {
              case R.id.ADD_BUTTON:
                  result =result.add(number) ;
                  number=result;
                  break;

              case R.id.SUBTRACT_BUTTON:
                  result =result.subtract(number) ;
                  number=result;
                  break;

              case R.id.MULTIPLY_BUTTON:
                  result =result.multiply(number) ;
                  number=result;
                  break;

              case R.id.DIVIDE_BUTTON:
                  try {
                      result =result.divide(number) ;
                  }catch (java.lang.ArithmeticException e){
                      result= (result.divide(number, 10, BigDecimal.ROUND_DOWN));
                  }

                  //result =result.divide(number) ;
                  number=result;
                  break;

              default:
                  assert false;
                  break;
          }
          //実験
          //number=0;
          FirstFlag=true;
          FlagPoint=false;
          smallKETA=1;

          //number = 0;
          i=0;
          show(result);
      }

      public void Equal(){
          switch (operatorId) {
              case R.id.ADD_BUTTON:
                  result =result.add(number) ;
                  number=result;
                  break;

              case R.id.SUBTRACT_BUTTON:
                  result =result.subtract(number) ;
                  number=result;
                  break;

              case R.id.MULTIPLY_BUTTON:
                  result =result.multiply(number) ;
                  number=result;
                  break;

              case R.id.DIVIDE_BUTTON:
                  try {
                      result =result.divide(number) ;
                  }catch (java.lang.ArithmeticException e){
                      result= (result.divide(number, 10, BigDecimal.ROUND_DOWN));
                  }
                  number=result;
                  break;

              default:
                  assert false;
                  break;
          }
          smallKETA=1;
          smallNumber= "0.";
          oldnumber= java.math.BigDecimal.valueOf(0);
          i=0;
          System.out.println("numberは"+number);
          show(result);
      }

      /**
       * クリア・ボタンがクリックされたときのハンドラー。
       * @param view
       */
      public void onClearButtonClick(View view) {
          number = java.math.BigDecimal.valueOf(0);
          result = java.math.BigDecimal.valueOf(0);
          operatorId = 0;
          FlagPoint=false;
          smallKETA=1;
          smallNumber= "0.";
          show(number);
      }

      public void onClearButtonClick2() {
          number = java.math.BigDecimal.valueOf(0);
          result = java.math.BigDecimal.valueOf(0);
          operatorId = 0;
          FlagPoint=false;
          smallNumber= "0.";
          smallKETA=1;
          show(number);
      }

      /**
       * 指定された数値を表示する。
       * @param number 表示する数値
       */
      private void show(java.math.BigDecimal number) {
          /*Double d = new Double(number);
          Integer i2= new Integer(d.intValue());
          Double d2= new Double(i2.doubleValue());

          if((d.doubleValue() - d2.doubleValue()==0)){
              TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
              resultView.setText(String.format("%,d",(int)number));
          }else{
              TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
              resultView.setText(Float.toString(number));
          }
          */
          java.math.BigDecimal seisuu=number.setScale(0, java.math.BigDecimal.ROUND_DOWN);
          java.math.BigDecimal shosu=number.subtract(seisuu);
          String stringShosu=shosu.toString().substring(1);

          java.text.NumberFormat nfNum =java.text.NumberFormat.getNumberInstance();

          TextView resultView = (TextView) findViewById(R.id.RESULT_VIEW);
          //java.math.BigDecimal a=new java.math.BigDecimal(nfNum.format(seisuu).toString());
          //resultView.setText(nfNum.format(number));
          if(shosu.compareTo(java.math.BigDecimal.ZERO)==0&&FlagEqual!=true) {
              resultView.setText(nfNum.format(seisuu).toString());
          }else{
              resultView.setText(nfNum.format(seisuu).toString() + stringShosu);
          }
          FlagEqual=false;
          System.out.println(nfNum.format(seisuu).toString()+stringShosu);
      }

      private void cKansuu(){
          if(i==0) {
              number = java.math.BigDecimal.valueOf(0);
              show(number);
              i++;
              return;
          }else if(i==1){
              onClearButtonClick2();
              i=0;
              return;
          }
      }

}
