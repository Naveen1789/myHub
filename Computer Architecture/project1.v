`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    19:01:36 06/12/2015 
// Design Name: 
// Module Name:    project1 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module project1(clk, enable, enableLed, switch, led, btn, cathodes, anodes);

	input clk, enable;
	input [3:0] switch;
	
	output enableLed;
	reg enableLed;
	output [3:0] led;
	reg [3:0] led;
	
	input [3:0] btn;
	
	output [6:0] cathodes;
	reg [6:0] cathodes;
	
	output [3:0] anodes;	
	reg [3:0] anodes;
	
	reg [3:0] sum0, sum1, sum2, sum3;
	
	reg [3:0] dig;
	
	reg slow_clock;
	
	integer count;
	
//	reg [3:0] btn0Pressed, btn1Pressed, btn2Pressed, btn3Pressed;
	
	reg [3:0] btn0Pressed = 4'b 0000;
	reg [3:0] btn1Pressed = 4'b 0000;
	reg [3:0] btn2Pressed = 4'b 0000;
	reg [3:0] btn3Pressed = 4'b 0000;
	
	reg disp;
		
	always @(posedge clk)
		begin
			if ( enable == 1 ) 
				begin				
					enableLed = 1;
					led = switch;
				end
			else
				begin	
					 enableLed = 0;
					led = 4'b0000;
				end
			create_slow_clock(clk, slow_clock);
		end

	always @(btn)
		begin		
			case (btn)
				4'b0001: begin		
								sum0 = switch;
								btn0Pressed = 4'b 1111;
							end
				4'b0010: begin
								sum1 = switch;
								btn1Pressed = 4'b 1111;
							end
				4'b0100: begin
								sum2 = switch;
								btn2Pressed = 4'b 1111;
							end
				4'b1000: begin
								sum3 = switch;
								btn3Pressed = 4'b 1111;
							end							
			endcase
		end
	
	always @(posedge slow_clock)
		begin
			// led=~led;
			if ((enable == 0)) anodes=4'b 1111;
			else
				begin
					case (anodes)
						4'b 0111: anodes=4'b 1011;
						4'b 1011: anodes=4'b 1101;
						4'b 1101: anodes=4'b 1110;
						4'b 1110: anodes=4'b 0111;
						default: anodes=4'b 0111;
					endcase

					case (anodes)
						4'b 0111: begin
										dig = sum0;
										disp = btn0Pressed;
									 end
						4'b 1011: begin
										dig = sum1;
										disp = btn1Pressed;
									 end
						4'b 1101: begin
										dig = sum2;
										disp = btn2Pressed;
									 end
						4'b 1110: begin
										dig = sum3;
										disp = btn3Pressed;
									 end
					endcase
					
					cathodes=calc_cathode_value(dig,disp);
				end
		end

	
	function [6:0] calc_cathode_value;
		input [3:0] dig;
		input [3:0] disp;
			if((disp != 0))
				begin
					begin
						case (dig)
							0: calc_cathode_value = 7'b 0000001;
							1: calc_cathode_value = 7'b 1001111;
							2: calc_cathode_value = 7'b 0010010;
							3: calc_cathode_value = 7'b 0000110;
							4: calc_cathode_value = 7'b 1001100;
							5: calc_cathode_value = 7'b 0100100;
							6: calc_cathode_value = 7'b 0100000;
							7: calc_cathode_value = 7'b 0001111;
							8: calc_cathode_value = 7'b 0000000;
							9: calc_cathode_value = 7'b 0000100;
							10: calc_cathode_value = 7'b 0000010;
							11: calc_cathode_value = 7'b 1100000;
							12: calc_cathode_value = 7'b 0110001;
							13: calc_cathode_value = 7'b 1000010;
							14: calc_cathode_value = 7'b 0010000;
							15: calc_cathode_value = 7'b 0111000;				
							default: calc_cathode_value = 7'b 1111111;
						endcase
					end
				end
			else
				begin
					calc_cathode_value = 7'b 1111111;
				end
	endfunction
	
	task create_slow_clock;
		input clock;
		inout slow_clock;
		integer count;
		
		begin
			if (count > 1000)
				begin
					count=0;
					slow_clock = ~slow_clock;
				end
			count = count+1;
		end
	endtask
	
endmodule


