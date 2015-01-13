<%@ page language="java"  import="java.net.URLDecoder" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String ip = request.getParameter("ip");
  //用于当前页面显示
  String ips=URLDecoder.decode(ip,"utf-8");
  //节点类型
  //String type=request.getParameter("type");
%>
<div class="easyui-layout" data-options="fit:true">
   <div data-options="region:'north',split:true,border:false" style="height:420px">
	<script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/exporting.js"></script>
    <script type='text/javascript'>
    $(function () {                                                                     
        $(document).ready(function() {
        	
        	Highcharts.createElement('link', {
        		href: 'http://fonts.googleapis.com/css?family=Dosis:400,600',
        		rel: 'stylesheet',
        		type: 'text/css'
        	}, null, document.getElementsByTagName('head')[0]);

        	Highcharts.theme = {
        		colors: ["#7cb5ec", "#f7a35c", "#90ee7e", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
        			"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
        		chart: {
        			backgroundColor: null,
        			style: {
        				fontFamily: "Dosis, sans-serif"
        			}
        		},
        		title: {
        			style: {
        				fontSize: '16px',
        				fontWeight: 'bold',
        				textTransform: 'uppercase'
        			}
        		},
        		tooltip: {
        			borderWidth: 0,
        			backgroundColor: 'rgba(219,219,216,0.8)',
        			shadow: false
        		},
        		legend: {
        			itemStyle: {
        				fontWeight: 'bold',
        				fontSize: '13px'
        			}
        		},
        		xAxis: {
        			gridLineWidth: 1,
        			labels: {
        				style: {
        					fontSize: '12px'
        				}
        			}
        		},
        		yAxis: {
        			minorTickInterval: 'auto',
        			title: {
        				style: {
        					textTransform: 'uppercase'
        				}
        			},
        			labels: {
        				style: {
        					fontSize: '12px'
        				}
        			}
        		},
        		plotOptions: {
        			candlestick: {
        				lineColor: '#404048'
        			}
        		},


        		// General
        		background2: '#F0F0EA'
        		
        	};
                                                                         
            Highcharts.setOptions(Highcharts.theme);                                                                       
            //var chart;   
                    	
            Highcharts.setOptions({                                                     
                global: {                                                               
                    useUTC: true                                                       
                }                                                                       
            });
            $('#container').highcharts({                                                
                chart: {                                                                
                    type: 'spline', 
                    animation: Highcharts.svg, // don't animate in old IE               
                    marginRight: 10,                                                    
                    events: {                                                           
                        load: function() {                                              
                                                                                   
                            // set up the updating of the chart each second             
                            var series = this.series[0];
                            setInterval(function() {         
                              $.getJSON("http://localhost:8080/WSN/highcharts.action",{ip:'<%=ips%>'}, function (data) {
                            	  var obj=data.rows;
                    
                            	  console.info(obj);
                           // 	  console.info(obj[0].dateCreated);
                           //	  console.info(obj[0].value);
                            	  var x = obj[0].dateCreated, // current time         
                                  y = parseFloat(obj[0].value);           
                            	  series.addPoint([x, y], true, true); 
                            	  //更新当前温度
                            	  $('#temp_chart_display').empty();
                            	  $('#temp_chart_display').append("<br/><span style='color:blue;font-size: 20px'>当前节点温度"+y+"°C</span>");
                              });
                                                
                            }, 10000);                                                   
                        }                                                               
                    }                                                                   
                },                                                                      
		           title: {
		               text: '无线传感器数据图',
		               style:{
		            		fontSize: '20px'
		               }
		               
		           },
/* 		           subtitle: {
		               text: '南京邮电大学',
		               style:{
		           		fontSize: '15px'
		              },
		               margin:50
		           },  */                                                                  
                xAxis: {                                                                
                	categories: [(new Date()).toLocaleString(),(new Date()).toLocaleString(),(new Date()).toLocaleString(),(new Date()).toLocaleString()]                                                   
                },                                                                      
                yAxis: {                                                                
                    title: {                                                            
                        text: 'Temperature (°C)'                                          
                    },                                                                  
                    plotLines: [{                                                       
                        //value: 0,                                                       
                        //width: 1,                                                       
                        color: '#808080'                                                
                    }]                                                                  
                },                                                                      
                tooltip: {
                	 valueSuffix: '°C'                                                                
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                legend: {                                                               
                    enabled: false                                                      
                },                                                                      
                exporting: {                                                            
                    enabled: false                                                      
                },                                                                      
                series: [{                                                              
                    name: '当前节点温度',                                                
                    data:[12,12,12,12]                                                                
                }]                                                                      
            });                                                                         
        });                                                                             
                                                                                        
    });                                                                                 				
		 </script>
         <div id="container" style="min-width:700px;height:400px"></div>
   </div>
   <div data-options="region:'west',split:true,border:true" style="text-align:center;width:450px">
            <br/><span style="font-size: 20px">当前节点IP：<%=ips%></span>
   </div>
   <div id='temp_chart_display' data-options="region:'center',border:true" style="text-align:center;width:50px">
   </div>
   </div> 